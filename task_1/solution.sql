INSERT INTO public.cat_colors_info (color, count)
SELECT 
    a.color,  (SELECT COUNT(b.color) FROM public.cats b WHERE b.color = a.color) AS count_
FROM
    public.cats a
GROUP BY color
ORDER BY count_