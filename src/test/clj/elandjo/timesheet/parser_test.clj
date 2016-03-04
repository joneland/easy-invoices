(ns elandjo.timesheet.parser-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [elandjo.timesheet.parser :refer [parse]]))

(def parser-test-input-file "parser-test.input")

(defn before []
  (spit parser-test-input-file "Jon\nThat Bank\n1 - 0\nMarch"))

(defn after []
  (io/delete-file parser-test-input-file))

(defn test-fixtures [run-test]
  (before)
  (run-test)
  (after))

(use-fixtures :each test-fixtures)

(deftest creates-timesheet-from-file-input 
  (is (= {:name "Jon"
          :client "That Bank"
          :days-worked[{:day 1 :time 1}{:day 2 :time "-"}{:day 3 :time 0}]
          :period "1 March - 3 March"} 
         (parse parser-test-input-file))))
