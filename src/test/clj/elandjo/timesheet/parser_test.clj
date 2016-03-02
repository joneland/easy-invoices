(ns elandjo.timesheet.parser-test
  (:require [clojure.test :refer :all]
            [elandjo.timesheet.parser :refer :all]))

(deftest creates-timesheet-data-structure 
  (is (= {:name "Jon"
          :client "That Bank"
          :days-worked [1 1 "-" "-" 0]}
         (parse-timesheet "Jon" "That Bank" "1 1 - - 0"))))
