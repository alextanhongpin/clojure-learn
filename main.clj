(println "hello world")

(defn describe-salary [{first :first-name
                         last :last-name
                         annual :salary
                         bonus :bonus-percentage
                         :or {bonus 5}}]
  (println first last "earns" annual "with a" bonus "percent-bonus"))

(def a-user {:first-name "john"
             :last-name "doe"
             :salary 1000
             :bonus-percentage 15})
(describe-salary a-user)

(def b-user {:first-name "john"
             :last-name "doe"
             :salary 1000})
(describe-salary b-user)
