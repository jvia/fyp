;; Configure Babel to support all languages included in the manuscript
(setq org-confirm-babel-evaluate nil)

;; Add a JSS-specific link type
;; (org-add-link-type
;;  "latex" nil
;;  (lambda (path desc format)
;;    (cond
;;     ((eq format 'html)
;;      (format "<span style=\"color:black;\">%s</span>" desc))
;;     ((eq format 'latex)
;;      (format "\\%s{%s}" path desc)))))

;; Set default header arguments for the Org-mode blocks used to
;; showcase example Org-mode syntax.
;; (setq org-babel-default-header-args:org '((:results . "raw silent")
;;                                           (:exports . "code")))

;; Replace nasty single-quotes returned by R.
;; (add-hook 'org-export-latex-final-hook
;;           (lambda ()
;;             (replace-regexp "’" "'")
;;             (goto-char (point-min))
;;             (replace-regexp "  \\\\texttt{SCHEDULED:} <2010-08-18 Wed>\n\n"
;;                             "   SCHEDULED: <2010-08-18 Wed>\n")
;;             (goto-char (point-min))
;;             (replace-regexp (regexp-quote ",*") "*")
;;             (replace-regexp (regexp-quote ",#") "#")))

;; Add a new export class to hold the JSS template.
;; (add-to-list 'org-export-latex-classes
;;              '("jss"
;;                "\\documentclass[article,shortnames]{jss}"
;;                ("\\section{%s}" . "\\section*{%s}")
;;                ("\\subsection{%s}" . "\\subsection*{%s}")
;;                ("\\subsubsection{%s}" . "\\subsubsection*{%s}")
;;                ("\\paragraph{%s}" . "\\paragraph*{%s}")
;;                ("\\subparagraph{%s}" . "\\subparagraph*{%s}")))

;; ;; JSS has its own code formatting style.
;; (setq org-export-latex-listings nil)
;; (setq org-export-latex-verbatim-wrap
;;       '("\\begin{Code}\n" . "\\end{Code}\n"))
