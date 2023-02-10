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
  (let [^LambdaLogger
        logger (.getLogger context)
        t0     (System/nanoTime)
        x      (charred.api/read-json in)
        t1     (System/nanoTime)
        y      (try
                 (let [body (get x "body")]
                   (if (true? (get x "isBase64Encoded"))
                     (String. (.decode (java.util.Base64/getDecoder) body))
                     body))
                 (catch Exception e
                   (.log logger (str "BODY-DECODE-ERROR: " (ex-message e)))
                   ""))
        t2     (System/nanoTime)]
    (.log logger (str "PROCESS_TIME: "
                      (/ (- t1 t0) 1000 1000.0) " "
                      (/ (- t2 t1) 1000 1000.0) " MS"))
    (charred.api/write-json out {:status 200 :message "OK" :body y})))
