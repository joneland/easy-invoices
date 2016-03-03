(ns elandjo.timesheet.enrichment)

(defn total-days-worked [timesheet]
  (assoc timesheet :total (reduce + (filter number? (:days-worked timesheet)))))

(defn days-in-month-vs-days-worked [timesheet]
  (let [days-worked (:days-worked timesheet)
        days-in-month (range 1 (inc (count days-worked)))]
    (->>
      (map #(into {} {:day % :time %2}) days-in-month days-worked)
      (assoc timesheet :days-worked))))

(defn enrich [timesheet]
  (->>
    timesheet
    (total-days-worked)
    (days-in-month-vs-days-worked)))
