(ns elandjo.main
  (:gen-class)
  (:require [clojure.java.io :refer :all]
            [elandjo.timesheet.transformation :refer :all] 
            [elandjo.timesheet.parser :refer :all] 
            [elandjo.timesheet.generation :refer :all]))

(defn generate-timesheet [input-file]
  (with-open [rdr (reader input-file)]
    (doseq [name (line-seq rdr)
            client (line-seq rdr)
            days-worked (line-seq rdr)]
      (->>
        (parse-timesheet name client days-worked)
         transform-timesheet
         html-timesheet
         pdf-timesheet))))

(defn -main [input-file]
  (generate-timesheet input-file))
