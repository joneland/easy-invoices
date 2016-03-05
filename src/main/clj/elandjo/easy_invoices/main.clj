(ns elandjo.easy-invoices.main
  (:gen-class)
  (:require [pdfkit-clj.core :refer [gen-pdf] :as pdfkit]
            [elandjo.easy-invoices.enrichment :refer [enrich] :as enricher] 
            [elandjo.easy-invoices.parser :refer [parse] :as parser] 
            [elandjo.easy-invoices.transformer :refer [html-timesheet] :as transformer]))

(defn to-pdf [html]
  (pdfkit/gen-pdf html 
    :tmp "pdfs"
    :stylesheets ["stylesheets/stylesheet.css"]))

(defn generate-timesheet [input-file]
  (->>
    (parser/parse input-file)
     enricher/enrich
     transformer/html-timesheet
     to-pdf))

(defn -main [input-file]
  (generate-timesheet input-file))
