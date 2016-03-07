(ns elandjo.easy-invoices.parser
  (:require [clojure.string :as string]))

(def content-keys [:name :client :days-worked :period :company-name :company-address :phone-number])

(defn apply-formatting [timesheet]
  (let [days-worked (->> 
                      (string/split (:days-worked timesheet) #" ")
                      (map #(if (nil? (re-find #"\d+" %)) % (Integer. %))))
        days-in-month (->> 
                        (count days-worked)
                        (inc)
                        (range 1))
        formatted-days-worked (map #(into {} {:day % :time %2}) days-in-month days-worked)
        formatted-period-worked (format "%d %s - %d %s" (first days-in-month) (:period timesheet) (last days-in-month) (:period timesheet))]
    (-> timesheet
      (assoc :days-worked formatted-days-worked)
      (assoc :period formatted-period-worked))))

(defn extract-contents [input-file required-details]
  (let [contents (-> (slurp input-file) (string/split #"\n"))]
    (->
     (zipmap content-keys contents)
     (select-keys required-details))))

(defn parse-timesheet [input-file]
  (let [timesheet (extract-contents input-file [:name :client :days-worked :period])]
    (apply-formatting timesheet)))

(defn parse-invoice [input-file]
  (let [invoice (extract-contents input-file [:company-name :company-address :phone-number])] 
    invoice))
