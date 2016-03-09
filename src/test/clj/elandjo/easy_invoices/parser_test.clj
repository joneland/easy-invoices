(ns elandjo.easy-invoices.parser-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [elandjo.easy-invoices.parser :refer [parse-timesheet parse-invoice]]))

(def parser-test-input-file "src/test/resources/parser-test.input")

(deftest parses-timesheet-from-file-input 
  (is (=
    {:name "Jon Eland"
     :client "Foobar LTD"
     :days-worked[{:day 1 :time 1}{:day 2 :time "-"}{:day 3 :time 0}]
     :period "1 March - 3 March"} 
    (parse-timesheet parser-test-input-file))))

(deftest parses-invoice-from-file-input
  (is (=
    {:company-name "Foobar Software LTD"
     :company-address "1 Foobar Road, London, Z1 2AB"
     :phone-number "01234-567890"
     :agency-address "The Agency, 5 Fizz Street, London, Z2 3DV"
     :attention-of "First Choice Agency Accounts"
     :email-to "first.choice@accounts.com"}
    (parse-invoice parser-test-input-file))))
