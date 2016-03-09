(ns elandjo.easy-invoices.parser
  (:require [clojure.string :as string]))

(def content-keys [:name :client :days-worked-tally :month :company-name :company-address :phone-number :agency-address])

(defn days-worked [timesheet]
  (->> 
    (string/split (:days-worked-tally timesheet) #" ")
    (map #(if (nil? (re-find #"\d+" %)) % (Integer. %)))))

(defn days-in-month [days-worked]
  (->> 
    (count days-worked)
    (inc)
    (range 1)))

(defn apply-timesheet-formatting [timesheet]
  (let [days-worked (days-worked timesheet)
        days-in-month (days-in-month days-worked)
        day-vs-time-worked (map #(into {} {:day % :time %2}) days-in-month days-worked)
        period-worked (format "%d %s - %d %s" (first days-in-month) (:month timesheet) (last days-in-month) (:month timesheet))]
    (-> timesheet
      (assoc :days-worked day-vs-time-worked)
      (assoc :period period-worked))))

(defn apply-invoice-formatting [invoice]
  invoice)

(defn extract-contents [input-file required-details]
  (let [contents (-> (slurp input-file) (string/split #"\n"))]
    (->
     (zipmap content-keys contents)
     (select-keys required-details))))

(defn parse-timesheet [input-file]
  (let [timesheet (extract-contents input-file [:name :client :days-worked-tally :month])]
    (-> (apply-timesheet-formatting timesheet)
        (dissoc :month :days-worked-tally))))

(defn parse-invoice [input-file]
  (let [invoice (extract-contents input-file [:company-name :company-address :phone-number :agency-address])] 
    (apply-invoice-formatting invoice)))
