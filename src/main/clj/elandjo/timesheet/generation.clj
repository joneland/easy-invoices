(ns elandjo.timesheet.generation
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css]]
            [pdfkit-clj.core :refer :all]))

(defn html-timesheet [details]
  (html [:html
          [:head (include-css "stylesheets/stylesheet.css")]
          [:body
            [:h1 (str "Name: " (:name details))]
            [:h1 (str "Client: " (:client details))]
            [:table
              [:tr
                [:td "Day"]
                [:td "Time (In Days)"]]
              (for [days-worked (:days-worked details)]
                [:tr
                  [:td (:day days-worked)]
                  [:td (:time days-worked)]])
              [:tr
                [:td "Total"]
                [:td (->> (:days-worked details)
                          (map :time)
                          (reduce +))]]]]]))

(defn pdf-timesheet [html]
  (gen-pdf html 
           :tmp "pdfs"
           :stylesheets ["stylesheets/stylesheet.css"]))
