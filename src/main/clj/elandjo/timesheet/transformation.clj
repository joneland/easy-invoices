(ns elandjo.timesheet.transformation)

(defn include-total-days-worked [timesheet-details]
  (assoc timesheet-details :total (reduce + (filter number? (:days-worked timesheet-details)))))

(defn format-days-worked [timesheet-details]
  (let [days-worked (:days-worked timesheet-details)]
    (->>
      (map #(into {} {:day % :time %2}) (range 1 (inc (count days-worked))) days-worked)
      (assoc timesheet-details :days-worked))))

(defn transform-timesheet [timesheet-details]
  (->>
    timesheet-details
    (include-total-days-worked)
    (format-days-worked)))
