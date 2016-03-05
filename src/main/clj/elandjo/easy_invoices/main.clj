(ns elandjo.easy-invoices.main
  (:gen-class)
  (:require [pdfkit-clj.core :refer [gen-pdf] :as pdfkit]
            [elandjo.easy-invoices.enrichment :refer [enrich-timesheet] :as enricher] 
            [elandjo.easy-invoices.parser :refer [parse-timesheet] :as parser] 
            [elandjo.easy-invoices.transformer :refer [html-timesheet] :as transformer]))

(defn to-pdf [html]
  (pdfkit/gen-pdf html 
    :tmp "pdfs"
    :stylesheets ["stylesheets/stylesheet.css"]))

(defn generate-timesheet [input-file]
  (->>
    (parser/parse-timesheet input-file)
     enricher/enrich-timesheet
     transformer/html-timesheet
     to-pdf))

(defn -main [input-file]
  (generate-timesheet input-file))
