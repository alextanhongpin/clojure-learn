(println "hello world")

; The double-colon is there to fully qualify keywords with your current namespace.
; user=> :foo
; :foo
; user=> ::foo
; :user/foo
(defn profit-rating [user]
  (let [ratings [::bronze ::silver ::gold ::platinum]]
    (nth ratings (rand-int (count ratings)))))

; Affiliate fee business rule
; affiliate | profit rating | fee (% of salary)
; mint.com | bronze | 0.03
; mint.com | silver | 0.04
; mint.com | gold/platinum | 0.05
; google.com | gold/platinum | 0.03

(defn fee-category [user]
  [(:referrer user) (profit-rating user)])

(defmulti profit-based-affiliate-fee fee-category)

(defmethod profit-based-affiliate-fee [:mint.com ::bronze] [user]
  (fee-amount 0.03 user))

(defmethod profit-based-affiliate-fee [:mint.com ::silver] [user]
  (fee-amount 0.04 user))

(defmethod profit-based-affiliate-fee [:mint.com ::gold] [user]
  (fee-amount 0.05 user))

(defmethod profit-based-affiliate-fee [:mint.com ::platinum] [user]
  (fee-amount 0.05 user))

(defmethod profit-based-affiliate-fee [:google.com ::gold] [user]
  (fee-amount 0.03 user))

(defmethod profit-based-affiliate-fee [:google.com ::platinum] [user]
  (fee-amount 0.03 user))

(defmethod profit-based-affiliate-fee :default [user]
  (fee-amount 0.02 user))
