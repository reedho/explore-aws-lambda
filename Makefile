.PHONY: build-HelloCljFunction

build-HelloCljFunction:
	clojure -T:build ci
	tar -zxf target/explore-aws-lambda-0.1.0.jar -C $(ARTIFACTS_DIR)
