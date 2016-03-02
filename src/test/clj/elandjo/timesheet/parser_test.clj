(ns elandjo.timesheet.parser-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [elandjo.timesheet.parser :refer :all]))

(defn test-setup [f]
  (spit "parser-test.input" "Jon\nThat Bank\n1 1 - - 0")
  (f)
  (io/delete-file "parser-test.input"))

(use-fixtures :once test-setup)

(deftest creates-timesheet-data-structure-from-file-input 
  (is (= {:name "Jon"
          :client "That Bank"
          :days-worked [1 1 "-" "-" 0]}
         (parse "parser-test.input"))))
