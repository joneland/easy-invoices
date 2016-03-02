(ns elandjo.timesheet.transformation-test
  (:require [clojure.test :refer :all]
            [elandjo.timesheet.transformation :refer :all]))

(deftest transforms-timesheet-data-ready-for-generation
  (is (= {:name "Jon"
          :client "That Bank"
          :days-worked [{:day 1 :time 1}{:day 2 :time 0}{:day 3 :time "-"}]
          :total 1}
         (transform-timesheet
           {:name "Jon"
            :client "That Bank"
            :days-worked [1 0 "-"]}))))
