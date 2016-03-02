(ns elandjo.main
  (:gen-class)
  (:require [elandjo.timesheet.transformation :refer :all] 
            [elandjo.timesheet.generation :refer :all]))

(defn -main[]
  (-> (transform-timesheet
        {:name "Jon"
         :client "That Bank"
         :days-worked [1 1 1 1 1 "-" "-" 1 1 1 1 0 "-" "-" 1 1 1 1 0 "-" "-" 1 1 0 1 1 "-" "-" 1 1 1]})
      html-timesheet
      pdf-timesheet))
