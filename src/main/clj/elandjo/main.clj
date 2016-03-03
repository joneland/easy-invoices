(ns elandjo.main
  (:gen-class)
  (:require [elandjo.timesheet.enrichment :refer [enrich] :as ts-enricher] 
            [elandjo.timesheet.parser :refer [parse] :as ts-parser] 
            [elandjo.timesheet.generation :refer [as-html] :as ts-generator]))

(defn as-pdf [html]
  (gen-pdf html
    :tmp "pdfs"
    :stylesheets ["stylesheets/stylesheet.css"]))

(defn generate-timesheet [input-file]
  (->>
    (ts-parser/parse input-file)
     ts-enricher/enrich
     ts-generator/as-html
     as-pdf))

(defn -main [input-file]
  (generate-timesheet input-file))
