# PaniniReadMore
Adds a `<!--more-->` tag to PaniniCMS, inspired by Blogger's "read more" tag.

After adding PaniniReadMore to your PaniniCMS installation, you need to tweak your home page a little bit to use the newly added variables by PaniniReadMore!

In your `home.html` you probably have this:
```
{{ post.content }}
```

Replace that with
```
{{ post.softMetadata.summary | raw }}
{% if post.softMetadata.hasSummary == true %}
<a href="{{ websiteUrl }}posts/{{ post.slug }}">Keep reading!</a>
{% endif %}
```
And that's it! Have fun!
