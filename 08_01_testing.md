# Testing

```clojure
(ns test.com (:require [clojure.test :refer :all]))

(is (= 2 (+ 1 1)))

(testing "arimethic"
  (testing "sum of two numbers"
    (is (= 2 (+ 1 1)))
    (is (= 3 (+ 1 2))))
  (testing "minus two numbers"
    (is (= 2 (- 3 1)))))
```
