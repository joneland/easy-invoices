(ns elandjo.timesheet.generation-test
  (:require [clojure.test :refer :all]
            [elandjo.timesheet.generation :refer :all]))

(def timesheet-details
  {:name "Jon"
   :client "That Bank"
   :days-worked [{:day 1 :time 1}{:day 2 :time 0}]
   :total 1})

(deftest timesheet-generation 
  "generates timesheet as html"
    (is (=
         "<html><head><link href=\"stylesheets/stylesheet.css\" rel=\"stylesheet\" type=\"text/css\" /></head><body><h3>Name: Jon</h3><h3>Client: That Bank</h3><table><tr><td>Day</td><td>Time (In Days)</td></tr><tr><td>1</td><td>1</td></tr><tr><td>2</td><td>0</td></tr><tr><td>Total</td><td>1</td></tr></table></body></html>"
         (as-html timesheet-details))))
