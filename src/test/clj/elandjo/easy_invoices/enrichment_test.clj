(ns elandjo.easy-invoices.enrichment-test
  (:require [clojure.test :refer :all]
            [elandjo.easy-invoices.enrichment :refer [enrich-timesheet enrich-invoice]]))

(deftest enriches-timesheet-with-and-total-days-worked
  (is (= {:days-worked [{:day 1 :time 1}
                        {:day 2 :time 0}
                        {:day 3 :time "-"}]
           :total-days-worked 1}
         (enrich-timesheet {:days-worked [{:day 1 :time 1}
                                {:day 2 :time 0}
                                {:day 3 :time "-"}]}))))

(deftest enriches-invoice-with-weekly-charge
  (is (= {:days-worked [{:day 1 :time "-"}
                        {:day 2 :time 1}
                        {:day 3 :time 0}
                        {:day 4 :time 1}
                        {:day 5 :time 1}
                        {:day 6 :time 1}
                        {:day 7 :time "-"}
                        {:day 8 :time "-"}
                        {:day 9 :time 1}]
          :rate 100.00
          :month "March 2016"
          :weekly-charge [{:week-commencing "2 March 2016" :days-worked 4 :net 400.00 :vat 80.00}
                          {:week-commencing "9 March 2016" :days-worked 1 :net 100.00 :vat 20.00}]}
         (enrich-invoice 
           {:days-worked [{:day 1 :time "-"}
                          {:day 2 :time 1}
                          {:day 3 :time 0}
                          {:day 4 :time 1}
                          {:day 5 :time 1}
                          {:day 6 :time 1}
                          {:day 7 :time "-"}
                          {:day 8 :time "-"}
                          {:day 9 :time 1}]
            :rate 100.00
            :month "March 2016"}))))
