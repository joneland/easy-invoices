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
         [:p (format "Work period: %s" (:period invoice))]
         [:p (format "Contractor rate: £%.2f per day" (:rate invoice))]
         [:table
          [:tr
           [:td "Week Commencing"]
           [:td "Days Worked"]
           [:td "Net Amount"]
           [:td "VAT Amount"]]
          (for [weekly-charge (:weekly-charge invoice)]
            [:tr
              [:td (:week-commencing weekly-charge)]
              [:td (format "%d @ £%.2f per day" (:days-worked weekly-charge) (:rate invoice))]
              [:td (format "£%.2f" (:net weekly-charge))]
              [:td (format "£%.2f" (:vat weekly-charge))]])]]]))
