(ns elandjo.easy-invoices.parser
  (:require [clojure.string :as string]))

(def content-keys [:name :client :days-worked-tally :month :company-name
                   :company-address :phone-number :agency-address :attention-of
                   :email-to :invoice-date :invoice-number])

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
                                              :attention-of :email-to :invoice-date :invoice-number])] 
    (-> invoice)))
