from django.contrib import admin
from .models import  Article

# Register your models here.

class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title' , 'content' , 'pub_time')
    # 过滤器
    list_filter = ('pub_time' ,)#只有一个元素的tuple也要加 逗号
admin.site.register(Article,ArticleAdmin)



