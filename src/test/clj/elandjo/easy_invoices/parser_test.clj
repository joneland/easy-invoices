(ns elandjo.easy-invoices.parser-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [elandjo.easy-invoices.parser :refer [parse-timesheet parse-invoice]]))

(def parser-test-input-file "parser-test.input")

(defn before []
  (spit parser-test-input-file "Jon\nThat Bank\n1 - 0\nMarch\nFoobar Software LTD\n1 Foobar Road, London, Z1 2AB\n01234-567890"))

(defn after []
  (io/delete-file parser-test-input-file))

(defn test-fixtures [run-test]
  (before)
  (run-test)
  (after))

(use-fixtures :each test-fixtures)

(deftest parses-timesheet-from-file-input 
  (is (=
    {:name "Jon"
     :client "That Bank"
     :days-worked[{:day 1 :time 1}{:day 2 :time "-"}{:day 3 :time 0}]
     :period "1 March - 3 March"} 
    (parse-timesheet parser-test-input-file))))

(deftest parses-invoice-from-file-input
  (is (=
    {:company-name "Foobar Software LTD"
     :company-address "1 Foobar Road, London, Z1 2AB"
     :phone-number "01234-567890"}
    (parse-invoice parser-test-input-file))))
