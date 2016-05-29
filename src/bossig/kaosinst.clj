(ns bossig.core)



(defn bassdrum [state ]
  (q/stroke 255)
  (q/stroke-weight 12)
  (q/fill (* 2 (get ( :ch state) :pan )) 10 10 )
  (dotimes [p 6]
    (q/with-translation [ (* p 300) (q/random 10 90)  (* 100 (mod4))]
      (q/box (* 3 (get (:bd state) :note)) )
      (q/with-translation [0 (* (mod8) 100)  0]
        (q/box (* 3 (get (:bd state) :note)) )))
    )

  )






(defn clicktrack [state]
  (q/stroke-weight (get (:sd state) :velocity))
  (q/stroke 100 50 210)
  (q/line (* (get (:ch state) :pan) 10) (* 1200 (q/noise (q/random 0.5))) 1920 (* 5000 (q/noise (q/random 20))) )
  )


(defn dynlines [state]
  (q/stroke 255)
  (q/stroke-weight 10)
  (dotimes [n (count @linesdyn)]
    (q/line (* n 10) (* (/ height (count @linesdyn)) n) (* -100 (get @linesdyn n))
            (* n 10) (* (/ height (count @linesdyn)) (+ 1 n)) (* -100 (get @linesdyn n)))
    ;(q/line (* n  100) 0 (* n 50)  1000)
    )
  )





(def kick (atom [0 1 0]))
(reset! kick [0 5 2 6 3 3.4 1 0 2 10 2 20 39 3 4  ] )


(def linesdyn (atom [0 1 2 1 3 1 0]))
