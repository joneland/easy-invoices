(ns elandjo.timesheet.enrichment)

(defn total-days-worked [timesheet]
  (->>
    (map :time (:days-worked timesheet))
    (filter number?)
    (reduce +)))

(defn enrich [timesheet]
  (assoc timesheet :total (total-days-worked timesheet)))
    
