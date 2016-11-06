(ns bossig.core)



(defn draw [state]
  (q/background  100 120 0)
  ;(println (:lines state))

  (q/with-translation [500 500 0]
    ;(circlejoy state)
    )
  (dotimes [n (count @kick)]
    (q/with-translation [(* n (/ width (count @kick))) (* (get @kick n) (* (tr) 20)) -1000]
      ;;(q/box (* 10 (get (:bd state) :note)) )
      (q/with-rotation [(*  (get @kick n) (get (:sd state) :velocity)) 1 0 1 ]
        (if (= n (mod8))
          (bassdrum state)
          ))
                                        ;      (clicktrack state)
      ))
  (q/with-translation [ 50 0 -100 ]
    (q/stroke-weight 1)
    ;(dynlines state)
    )

 ; (sixtyliner [0 0 1000 1000] )

;  (wavelines 1000 5 2 3 1 200 1200 14 10 1000 100 10 )

  (addpill (* (* 20 (mod4)) (get (:ld1 state) :note)) (* -20 (get (:ld1 state) :velocity)) 10  20)
  (updatepills) ;;;pillstate is a bit boring at the moment....
  (q/with-translation [500 500 (* -10 (get (:ch state) :pan ))]
    (renderpills state)
    )


  (q/fill (* 2 (get (:ch state) :note )) 10 10 )
  (q/box (* 3 (get (:ch state) :note)))
 ; (q/with-rotation [(get (:ld1 state) :note ) 0 1 0])


  (dotimes [ p 5]
    (q/fill 20 30 24 23)
    (dolines (+ 25 (* p 300)) 0 (* (tr) (* p -100)) 200 1000 0 0.2 40 (+ 10 9) (*  p  (mod4)) 0 (* 5 15) 34 40)
    )


  (.sendScreen @server))




(def trans (atom 0) )
 (defn renderpills [state]
(let [chord  (get (:chords state ) :note) ]
   ;; (println chord)
    (case chord
      44 (reset! trans 200 )
      46 (reset! trans 0 )
      47 (reset! trans  40)
      49 (reset! trans 700 )
      52 (reset! trans  340)
      (reset! trans 0 )
      )
    )
(q/with-translation [0 @trans 0]
  (dotimes [n (count @pills)]
    (q/with-translation [(get (nth @pills n) :x ) (get (nth @pills n) :y )  (* 10  (get (nth @pills n) :z ))]
      (q/fill 45 23 (q/random 230))
      (if (= 0 (mod n 2))
        (q/box (* 50 (get (:sd state) :note) ) @trans 40 )
        (q/box 5 100 40 )
                                        ;(q/box (* 100 (get (:ld1 state) :note )) 50 50)
        )
      )
    ))
  )

(defn doline [p1 p2]
  (q/line p1 p2)
  )


(defn sixtyliner [p1p2 & args]
 ; (println p1p2 )
 ;; (println (first (into [] args)))
  (let [ [x1 y1 x2 y2] (first (into [] args))]
    (q/line x1 y1 x2 y2 )
    )
  )

(defn dolines [x y z width height frame size res noisetop noisebottom colorshift r g b ]
  (q/stroke-weight 1)
  (if (= 0 colorshift)
    (q/stroke r g b)
    (q/stroke (* 15 (tr))))
  (q/with-translation [x y z]
    (if (= frame 0) (q/rect 0 0 width height))
    (reduce sixtyliner (into [] (map (fn [a]
                                        ;;(println (q/noise (* a noisetop)))
                                          [(* width  (q/noise (* a noisetop))) 0 (* width (q/noise (* a noisebottom))) height])
                                        (range 0 size (/ size res))))))


)




;;; TODO add notesmap in midifilters to map range across screen
;;;
