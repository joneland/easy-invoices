(ns elandjo.timesheet.enrichment-test
  (:require [clojure.test :refer :all]
            [elandjo.timesheet.enrichment :refer [enrich]]))

(deftest enriches-timesheet-with-days-and-total
  (is (= {:days-worked [{:day 1 :time 1}{:day 2 :time 0}{:day 3 :time "-"}]
           :total 1}
         (enrich {:days-worked [1 0 "-"]}))))
