(ns elandjo.easy-invoices.enrichment)

(defn total-days-worked [timesheet]
  (->>
    (map :time (:days-worked timesheet))
    (filter number?)
    (reduce +)))

(defn enrich-timesheet [timesheet]
  (assoc timesheet :total-days-worked (total-days-worked timesheet)))

(defn enrich-invoice [invoice]
  (assoc invoice :weekly-charge [{:week-commencing "2 March 2016" :days-worked 4 :net 400.00 :vat 80.00}
                                 {:week-commencing "9 March 2016" :days-worked 1 :net 100.00 :vat 20.00}]))
