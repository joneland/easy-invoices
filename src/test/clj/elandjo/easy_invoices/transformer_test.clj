(ns elandjo.easy-invoices.transformer-test
  (:require [clojure.test :refer :all]
            [elandjo.easy-invoices.transformer :refer [html-timesheet html-invoice]]))

(defn strip-newlines-and-tabs [file]
  (-> (slurp file)
      (clojure.string/replace "\n" "")
      (clojure.string/replace "\t" "")))

(def timesheet
  {:name "Jon"
   :client "That Bank"
   :days-worked [{:day 1 :time 1}{:day 2 :time 0}]
   :total-days-worked 1
   :period "1 March - 2 March"})

(deftest transforms-timesheet-to-html
  (is (=
    (strip-newlines-and-tabs "src/test/resources/expected-timesheet.html")
    (html-timesheet timesheet))))

(def invoice
  {:company-name "Foobar Software LTD"
   :company-address "1 Foobar Road, London, Z1 2AB"
   :phone-number "01234-567890"
   :agency-address "The Agency, 5 Fizz Street, London, Z2 3DV"
   :attention-of "First Choice Agency Accounts"
   :email-to "first.choice@accounts.com"
   :invoice-date "1 April 2016"
   :invoice-number "34"
   :period "1 March - 3 March"
   :rate 100.00
   :weekly-charge [{:week-commencing "1 March 2016" :days-worked 4 :net 400.00 :vat 80.00}
                   {:week-commencing "8 March 2016" :days-worked 5 :net 500.00 :vat 100.00}]})

(deftest transforms-invoice-to-html
  (is (=
    (strip-newlines-and-tabs "src/test/resources/expected-invoice.html")
    (html-invoice invoice))))
