(ns elandjo.easy-invoices.transformer
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css]]))

(defn html-timesheet [timesheet]
  (html
    [:html
      [:head (include-css "stylesheets/stylesheet.css")]
        [:body
          [:h3 (format "Name: %s" (:name timesheet))]
          [:h3 (format "Client: %s" (:client timesheet))]
          [:h3 (format "Period: %s" (:period timesheet))]
          [:table
            [:tr
              [:td "Day"]
              [:td "Time (In Days)"]]
            (for [days-worked (:days-worked timesheet)]
              [:tr
                [:td (:day days-worked)]
                [:td (:time days-worked)]])
            [:tr
              [:td "Total"]
              [:td (:total-days-worked timesheet)]]]]]))
