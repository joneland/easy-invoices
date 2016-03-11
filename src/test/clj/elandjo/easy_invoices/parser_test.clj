(ns elandjo.easy-invoices.parser-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [elandjo.easy-invoices.parser :refer :all]))

(def parser-test-input-file "src/test/resources/parser-test.input")

(deftest parses-timesheet-from-file-input 
  (is (=
    {:name "Jon Eland"
     :client "Foobar LTD"
     :days-worked[{:day 1 :time 1}{:day 2 :time "-"}{:day 3 :time "-"}{:day 4 :time 0}]
     :period "1 March - 4 March"} 
    (parse-timesheet parser-test-input-file))))

(deftest parses-weeks-worked
  (is (= [{:week-commencing 2 :days-worked 3}
          {:week-commencing 9 :days-worked 4}]
         (weeks-worked {:month "March 2016"
                        :days-worked-tally "- 1 1 0 1 0 - - 0 1 1 1 1 - -"}))))

(deftest parses-invoice-from-file-input
  (is (=
    {:company-name "Foobar Software LTD"
     :company-address "1 Foobar Road, London, Z1 2AB"
     :phone-number "01234-567890"
     :agency-address "The Agency, 5 Fizz Street, London, Z2 3DV"
     :attention-of "First Choice Agency Accounts"
     :email-to "first.choice@accounts.com"
     :invoice-date "1 April 2016"
     :invoice-number "34"
     :period "1 March - 4 March"
     :weeks-worked [{:week-commencing "1 March" :days-worked 1}
                    {:week-commencing "4 March" :days-worked 0}]
     :rate 100.00}
    (parse-invoice parser-test-input-file))))
