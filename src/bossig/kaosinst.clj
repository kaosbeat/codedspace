(ns bossig.core)



;;helpers
(def tr (seq->stream (cycle-between 1 1 16 0.1 15)))




(defn drop-nth [n coll]
  ;;keep indexed changes coll from [] to () so added (vec)
  (vec (keep-indexed #(if (not= %1 n) %2) coll))

  ;  (if (= n 0)
  ;  (vec (drop 1 coll))
  ;(if (= n (count coll))
  ;  (pop coll)
  ;  (vec (concat (subvec coll 0 n) (subvec coll (+ 1 n) (count coll))))))
  )




;; buffers go here
(defonce buf-0 (buffer 8))
;;to get data from a buffer, do (buffer-get buf-0 7)
(defonce buf-1 (buffer 8))


(buffer-write! buf-0 [1 0 1 1 0 0 1 0])
(buffer-write! buf-1 [10 23 23 4 5 6 7 7])


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


(defn bassdrum [state]
  (q/box 50)
  )




(defn clicktrack [state]
  (q/stroke-weight (get (:sd state) :velocity))
  (q/stroke 100 50 210)
  (q/line (* (get (:ch state) :pan) 10) (* 1200 (q/noise (q/random 0.5))) 1920 (* 5000 (q/noise (q/random 20))) )
  )



(def linesdyn (atom [0 1 2 7 4 5 -1 -4 1 0]))
(defn dynlines [state]
  (q/stroke (* 15 (tr)) 255 255 128)
  (q/stroke-weight 10)
  (dotimes [p 10]
          (q/with-translation [(* p (tr)) 0 0 ]
            (dotimes [n (-   (count @linesdyn) 1)]
              (q/with-rotation [ (q/random 0 0) 1 0 0]

                (q/line (* n 10) (* (/ (q/height) (count @linesdyn)) n) (* -300 (get @linesdyn n))
                        (* n 10) (* (/ (q/height) (count @linesdyn)) (+ 1 n)) (* (* (mod4) -300) (get @linesdyn n)))
                (q/line (* n 10) (* (/ (q/height) (count @linesdyn)) (+ 1 n)) (* -300 (get @linesdyn n))
                        (* n 10) (* (/ (q/height) (count @linesdyn)) (+ 1 n)) (* -300 (get @linesdyn (+ 1 n)))
                        ))
                                        ;(q/line (* n  100) 0 (* n 50)  1000)
              )))
  )

(event-debug-off)

(tr)


(defn tester  [bottomsegments minbottom maxbottom waveint]
  (range minbottom (rand-int maxbottom ) waveint ))

(defn tester []
  (fn this [a b] (+ a b) ))

(defn tester [start end step]
  (concat start  (range  (last start) end step))
  )

( take 3 (iterate  (tester [1] 20 2)) 3)

(tester 1 5 200 3)

(defn tester []
  (let [x [0 200 150 500 200 0]
        min 0
        max 300
        ]
    (dotimes [n 6]

      )
    )
  )




(defn wavelines [wavewidth waveint topsegments bottomsegments starttop mintop maxtop startbottom minbottom maxbottom color offset]


  (q/stroke-weight 1)
  (let [top [starttop]
        bottom [startbottom]
        ]
    (dotimes [t topsegments]
      (let [x (q/random mintop maxtop)]
        ;(println)
        (into [] (concat top (range (last top) x waveint )))
        ((if (= t topsegments)
           ()
           else-expr) )
        )

      )


    (dotimes [b bottomsegments]
      (let [x (q/random minbottom maxbottom)]
        ( into [] (concat bottom (range (last bottom) x waveint )))
        ))


    (dotimes [n 10]
      (q/line (* n 5) (nth top 0)
              (* n 5) (nth bottom 0)
              )
      ))

  )



(defn circlejoy [state]
  (dotimes [n (mod16)]
    (q/with-translation [ 0 0 (* -300 (+ 0 n))]
      (q/ellipse 0 0 (* n 200) (* n 200))))

  )



(def kick (atom [0 1 0]))
(reset! kick [0 5 2 6 3 3.4 1 0 2 10 2 20 39 3 4  ] )
(reset! kick [0 1 5 6 4 6  ] )


;define structure to hold pills
(def pills (atom [{:size 10 :x 100 :y 100 }  {:size 20 :x 200 :y 200 }  ]))
(reset! pills [])
;addpill to atom
(defn addpill [x y ttl]
  (if (= 0 (count @pills))
    (reset! pills []))
  (swap! pills conj {:x x :y y :ttl ttl })
  )

(addpill 250 500 312  )
(addpill 25 2 30  )
(addpill 25 3 42  )
(addpill 25 4 50 )

(def pillcount ( atom []))

(defn updatepills []
  ; for some reason not all pills are deleted
  (reset! pillcount [])
  (dotimes [n (count @pills)]
    (if (false? (= 0 (get (get @pills n) :ttl)))
      ;decrease TTL in pill if ttl > 0
      (swap! pills update-in [n :ttl] dec)
      ;else mark pill for deletion
      (swap! pillcount conj n)
      ;(reset! @pills [0 9 0])
      )
    )
  (dotimes [n (count @pillcount)]
    (reset! pills  (drop-nth (nth @pillcount n) @pills)))
  ;(println @pills)
  )

;(updatepills)
(defn renderpills [state]
  (q/no-stroke)
  (dotimes [n (count @pills)]
    (q/with-translation [(get (nth @pills n) :x) (get (nth @pills n) :y) -100]
      (q/fill (* 4 (get (nth @pills n)  :ttl)))
      (q/ellipse 0 0 50 50)
      (q/rect 0 -25 50 50)
      (q/ellipse 50 0 50 50)))
  (q/stroke 255)
  )



(defn renderpills [state]

(dotimes [n (count @pills)]
    (q/with-translation [(get (nth @pills n) :x) (get (nth @pills n) :y) -100]
      (q/fill 24 130 23 (* 4 (get (nth @pills n)  :ttl)))
      (if (= 0 (mod2))
        (q/box 20 20 1000)
        (q/box 20 1000 20))
      ))

  )
