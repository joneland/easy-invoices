(ns elandjo.main
  (:gen-class)
  (:require [elandjo.timesheet.generation :refer :all]))

(defn -main[]
  (-> (html-timesheet {:name "Jon"
                       :client "That Bank"
                       :days-worked [
                                     {:day 1 :time 1}
                                     {:day 2 :time 1}
                                     {:day 3 :time 1}
                                     {:day 4 :time 1}
                                     {:day 5 :time 1}
                                     {:day 6 :time 0}
                                     {:day 7 :time 0}
                                     {:day 8 :time 1}
                                     {:day 9 :time 1}
                                     {:day 10 :time 1}
                                     {:day 11 :time 1}
                                     {:day 12 :time 1}
                                     {:day 13 :time 0}
                                     {:day 14 :time 0}
                                    ]})
      pdf-timesheet))
