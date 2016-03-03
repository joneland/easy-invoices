(ns elandjo.timesheet.parser
  (:require [clojure.string :as string]))

(defn format-days-worked [input]
  (assoc input :days-worked (map #(if (nil? (re-find #"\d+" %)) % (Integer. %)) (string/split (:days-worked input) #" "))))

(defn parse [input-file]
  (let [contents (slurp input-file)]
    (->>
      (string/split contents #"\n")
      (zipmap [:name :client :days-worked :period])
      format-days-worked)))
