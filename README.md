# explore-aws-lambda

This repo was created with the following command:

    $ clj -Tnew app :name me.explore/aws-lambda :target-dir explore-aws-lambda

## Usage

Run the project directly, via `:exec-fn`:

    $ clojure -X:run-x
    Hello, Clojure!

Run the project, overriding the name to be greeted:

    $ clojure -X:run-x :name '"Someone"'
    Hello, Someone!

Run the project directly, via `:main-opts` (`-m me.explore.aws-lambda`):

    $ clojure -M:run-m
    Hello, World!

Run the project, overriding the name to be greeted:

    $ clojure -M:run-m Via-Main
    Hello, Via-Main!

Run the project's tests:

    $ clojure -T:build test

Run the project's CI pipeline and build an uberjar:

    $ clojure -T:build ci

This will produce an updated `pom.xml` file with synchronized dependencies
inside the `META-INF` directory inside `target/classes` and the uberjar in
`target`. You can update the version (and SCM tag) information in generated
`pom.xml` by updating `build.clj`.

If you don't want the `pom.xml` file in your project, you can remove it. The
`ci` task will still generate a minimal `pom.xml` as part of the `uber` task,
unless you remove `version` from `build.clj`.

Run that uberjar:

    $ java -jar target/aws-lambda-0.1.0.jar

## Deploy as AWS Lambda

To be a valid lambda, we only need small changes. First, in our main ns, we
instruct that this ns implements
`com.amazonaws.services.lambda.runtime.RequestStreamHandler` interface, and
provide corresponding implementation of its `handleRequest` method.

```clojure
(ns me.explore.aws-lambda
  (:require [charred.api])
  (:import [com.amazonaws.services.lambda.runtime
            Context LambdaLogger])
  (:gen-class
   :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler]))
```

```clojure
(defn -handleRequest
  [_this
   ^java.io.InputStream in
   ^java.io.OutputStream out
   ^Context context]
  (let [^LambdaLogger logger (.getLogger context)
        x (charred.api/read-json in)]
    (.log logger (str "INPUT-STREAM-DATA" (charred.api/write-json-str x)))
    (charred.api/write-json out {:status 200 :message "OK" :in x})))
```

Build & Deploy:

Given this [`template.yml`](./template.yml) and the companion
[`Makefile`](./Makefile) we can now deploy with [SAM](https://aws.amazon.com/serverless/sam/).

    $ sam build
    $ sam deploy --guided # for the firstime, this will create samconfig.toml
    $ sam deploy # subsequent deploy

Cleanup:

    $ sam delete --config-file samconfig.toml


## License

Copyright © 2023 Ridho

Distributed under the Eclipse Public License version 1.0.
