var gulp = require('gulp');

var connect = require('gulp-connect');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-minify-css');
var clean = require('gulp-clean');
var jshint = require('gulp-jshint');
var concat = require('gulp-concat');
var watch = require('gulp-watch');


gulp.task('connect', function () {
  connect.server({
    root: 'dist/',
    port: 8000
  });
});

gulp.task('clean', function() {
	gulp.src('dist/*').pipe(clean({ force: true }));
});

gulp.task('minify-css', function() {
  gulp.src(['app/**/*.css', '!app/bower_components/**'])
    .pipe(minifyCSS({ comments:true, spare:true }))
    .pipe(gulp.dest('dist/'));
});

gulp.task('minify-js', function() {
	gulp.src(['app/**/*.js', '!app/bower_components/**'])
//    .pipe(uglify({mangle: false}))
    .pipe(concat('app.js'))
		.pipe(gulp.dest('dist/'));
    
});

gulp.task('copy-html-files', function () {
  gulp.src('app/**/*.html')
    .pipe(gulp.dest('dist/'));
});

gulp.task('copy-bower-components', function () {
  gulp.src('app/bower_components/**')
    .pipe(gulp.dest('dist/bower_components'));
});

gulp.task('lint', function() {
  gulp.src(['app/**/*.js', '!app/bower_components/**'])
    .pipe(jshint())
    .pipe(jshint.reporter('default'))
    .pipe(jshint.reporter('fail'));
});

gulp.task('watch', function() {
  gulp.watch('app/**/*.html', ['copy-html-files']);
  gulp.watch('app/**/*.js', ['minify-js']);
  gulp.watch('app/**/*.css', ['minify-css']);
});

gulp.task('build', ['lint', 'minify-js', 'minify-css', 'copy-html-files', 'copy-bower-components']);

gulp.task('default', ['build', 'connect', 'watch']);