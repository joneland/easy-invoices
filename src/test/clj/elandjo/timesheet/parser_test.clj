(ns elandjo.timesheet.parser-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [elandjo.timesheet.parser :refer :all]))

(def parser-test-input-file "parser-test.input")

(defn before []
  (spit parser-test-input-file "Jon\nThat Bank\n1 1 - - 0"))

(defn after []
  (io/delete-file parser-test-input-file))

(defn test-fixtures [run-test]
  (before)
  (run-test)
  (after))

(use-fixtures :each test-fixtures)

(deftest creates-timesheet-data-structure-from-file-input 
  (is (= {:name "Jon"
          :client "That Bank"
          :days-worked [1 1 "-" "-" 0]}
         (parse parser-test-input-file))))
