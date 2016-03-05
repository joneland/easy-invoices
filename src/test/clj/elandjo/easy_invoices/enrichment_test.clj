(ns elandjo.easy-invoices.enrichment-test
  (:require [clojure.test :refer :all]
            [elandjo.easy-invoices.enrichment :refer [enrich-timesheet]]))

(deftest enriches-timesheet-with-and-total-days-worked
  (is (= {:days-worked [{:day 1 :time 1}
                        {:day 2 :time 0}
                        {:day 3 :time "-"}]
           :total-days-worked 1}
         (enrich-timesheet {:days-worked [{:day 1 :time 1}
                                {:day 2 :time 0}
                                {:day 3 :time "-"}]}))))
