(ns elandjo.timesheet.transformer
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css]]))

(defn to-html [timesheet]
  (html
    [:html
      [:head (include-css "stylesheets/stylesheet.css")]
        [:body
          [:h3 (str "Name: " (:name timesheet))]
          [:h3 (str "Client: " (:client timesheet))]
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
              [:td (:total timesheet)]]]]]))
