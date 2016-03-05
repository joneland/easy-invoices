(ns elandjo.easy-invoices.parser
  (:require [clojure.string :as string]))

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

(defn parse [input-file]
  (let [contents (-> (slurp input-file) (string/split #"\n"))
        timesheet (zipmap [:name :client :days-worked :period] contents)]
    (apply-formatting timesheet)))
