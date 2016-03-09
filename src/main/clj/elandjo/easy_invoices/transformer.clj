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

(defn html-invoice [invoice]
  (html
    [:html
      [:head (include-css "stylesheets/stylesheet.css")]
        [:body
         [:h3 (:company-name invoice)]
         [:h3 (:company-address invoice)]
         [:h3 (:phone-number invoice)]
         (for [line-of-address (clojure.string/split (:agency-address invoice) #", ")]
           [:p line-of-address])
         [:p (format "For the attention of: %s" (:attention-of invoice))]
         [:p (format "Emailed to: %s" (:email-to invoice))]
         [:p (format "Invoice date: %s" (:invoice-date invoice))]
         [:p (format "INVOICE NO: %s" (:invoice-number invoice))]
         [:p (format "Work period: %s" (:period invoice))]]]))
