(ns elandjo.timesheet.parser
  (:require [clojure.string :as string]))

(defn parse-days-worked [input]
 (let [days-worked (map #(if (nil? (re-find #"\d+" %)) % (Integer. %)) (string/split (:days-worked input) #" "))
       days-in-month (range 1 (inc (count days-worked)))]
    (->>
      (map #(into {} {:day % :time %2}) days-in-month days-worked)
      (assoc input :days-worked))))

(defn parse [input-file]
  (let [contents (slurp input-file)]
    (->>
      (string/split contents #"\n")
      (zipmap [:name :client :days-worked])
      parse-days-worked)))
