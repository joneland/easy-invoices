(defproject easy-invoices "0.1-SNAPSHOT"
  :description "creating invoices each month"
  :url "https://github.com/joneland/easy-invoices"
  :source-paths ["src/main/clj" ]
  :test-paths [ "src/test/clj" ]
  :main elandjo.easy-invoices.main
  :dependencies
  [[org.clojure/clojure "1.8.0"]
   [org.clojars.runa/conjure "2.1.3"]
   [hiccup "1.0.5"]
   [pdfkit-clj "0.1.5"]])
