(println "hello world")

(defn add [x y] {:pre [(> x 0) (> y 0)]
                 :post [(> % 0)]}
  (+ x y))
