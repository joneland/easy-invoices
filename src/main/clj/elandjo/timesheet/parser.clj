(ns elandjo.timesheet.parser
  (:require [clojure.java.io :refer :all]
            [clojure.string :as string]))

(defn parse-days-worked [days-worked]
  (map #(if (nil? (re-find #"\d+" %)) % (Integer. %)) (string/split days-worked #" ")))

(defn parse-timesheet [name client days-worked]
  (into {} {:name name :client client :days-worked (parse-days-worked days-worked)}))
