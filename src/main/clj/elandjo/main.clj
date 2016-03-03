(ns elandjo.main
  (:gen-class)
  (:require [pdfkit-clj.core :refer [gen-pdf] :as pdfkit]
            [elandjo.timesheet.enrichment :refer [enrich] :as ts-enricher] 
            [elandjo.timesheet.parser :refer [parse] :as ts-parser] 
            [elandjo.timesheet.transformer :refer [to-html] :as ts-transformer]))

(defn to-pdf [html]
  (pdfkit/gen-pdf html
    :tmp "pdfs"
    :stylesheets ["stylesheets/stylesheet.css"]))

(defn generate-timesheet [input-file]
  (->>
    (ts-parser/parse input-file)
     ts-enricher/enrich
     ts-transformer/to-html
     to-pdf))

(defn -main [input-file]
  (generate-timesheet input-file))
