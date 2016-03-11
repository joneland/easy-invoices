(ns elandjo.easy-invoices.parser
  (:require [clojure.string :as string]))

(def content-keys [:name :client :days-worked-tally :month :company-name
                   :company-address :phone-number :agency-address :attention-of
                   :email-to :invoice-date :invoice-number :rate])

(defn days-worked [timesheet]
  (->> 
    (string/split (:days-worked-tally timesheet) #" ")
    (map #(if (nil? (re-find #"\d+" %)) % (Integer. %)))))

(defn days-in-month [days-worked]
  (->> 
    (count days-worked)
    (inc)
    (range 1)))

(defn day-vs-time-worked [timesheet]
  (let [days-worked (days-worked timesheet)
        days-in-month (days-in-month days-worked)]
    (map #(into {} {:day % :time %2}) days-in-month days-worked)))

(defn period-worked [timesheet]
  (let [days-worked (days-worked timesheet)
        days-in-month (days-in-month days-worked)]
    (format "%d %s - %d %s" (first days-in-month) (:month timesheet) (last days-in-month) (:month timesheet))))

(defn weeks-worked [invoice]
  (let [day-vs-time-worked (vec (day-vs-time-worked invoice))
        include-week-commencing (reduce
                                  (fn [result current-day]
                                    (let [yesterday (last result)
                                          week-commencing (if (number? (:time yesterday))
                                                            (:week-commencing yesterday)
                                                            (:day current-day))]
                                      (conj result (assoc current-day :week-commencing week-commencing))))
                                  []
                                  day-vs-time-worked)]
    (->>
      (filter #(number? (:time %)) include-week-commencing)
      (map #(dissoc % :day)))))

(defn extract-contents [input-file required-details]
  (let [contents (-> (slurp input-file) (string/split #"\n"))]
    (->
     (zipmap content-keys contents)
     (select-keys required-details))))

(defn parse-timesheet [input-file]
  (let [timesheet (extract-contents input-file [:name :client :days-worked-tally :month])]
    (-> timesheet
      (assoc :days-worked (day-vs-time-worked timesheet))
      (assoc :period (period-worked timesheet))
      (dissoc :month :days-worked-tally))))

(defn parse-invoice [input-file]
  (let [invoice (extract-contents input-file [:company-name :company-address :phone-number :agency-address
                                              :attention-of :email-to :invoice-date :invoice-number
                                              :month :days-worked-tally :rate])] 
    (-> invoice
      (assoc :period (period-worked invoice))
      (assoc :rate (Double/parseDouble (:rate invoice)))
      (assoc :weeks-worked [{:week-commencing "1 March", :days-worked 1}
                            {:week-commencing "4 March", :days-worked 0}])
      (dissoc :month :days-worked-tally))))
