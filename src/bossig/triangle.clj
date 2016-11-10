(ns bossig.core)



(defn draw [state]
  (q/background  100 120 0)
  ;(println (:lines state))
(circlejoy state)
  (q/with-translation [500 500 0]
                                        ;(circlejoy state)
;    (drawboxinplace @midimap8 @midimap8map state)
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
(drawinplace @midichords state)

 ; (sixtyliner [0 0 1000 1000] )

;  (wavelines 1000 5 2 3 1 200 1200 14 10 1000 100 10 )

  (addpill (* (* 2 (mod4)) (get (:ld1 state) :note)) (* 2 (get (:ld1 state) :velocity)) 10  10)
  (updatepills) ;;;pillstate is a bit boring at the moment....
  (q/with-translation [50 50 (* 0 (get (:ch state) :pan ))]
    (renderpills state)
    )


  (q/fill (* 2 (get (:ch state) :note )) 10 10 )
  (q/box (* 3 (get (:ch state) :note)))
 ; (q/with-rotation [(get (:ld1 state) :note ) 0 1 0])



  (dotimes [ p (count @midimap8)]
    (q/fill 20 30 24 23)
                                        ;(dolines (+ 25 (* p 300)) 0 (* (tr) (* p -100)) 200 1000 0 0.2 40 (+ 10 9) (*  p  (mod4)) 0 (* 5 15) 34 40)


    )


  (.sendScreen @server))
