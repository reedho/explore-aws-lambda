(ns me.explore.aws-lambda
  (:require [charred.api])
  (:import [com.amazonaws.services.lambda.runtime
            Context LambdaLogger])
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler]))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

(defn -handleRequest
  [_this
   ^java.io.InputStream in
   ^java.io.OutputStream out
   ^Context context]
  (let [^LambdaLogger logger (.getLogger context)
        x (charred.api/read-json in)]
    (.log logger (str "INPUT-STREAM-DATA" (charred.api/write-json-str x)))
    (charred.api/write-json out {:status 200 :message "OK" :in x})))
