/*global module:false*/
module.exports = function(grunt) {
  'use strict';
  require('time-grunt')(grunt);
  require('load-grunt-tasks')(grunt);

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    banner: '/*! <%= pkg.title || pkg.name %> - v<%= pkg.version %> - ' +
      '<%= grunt.template.today("yyyy-mm-dd") %>\n' +
      '<%= pkg.homepage ? "* " + pkg.homepage + "\\n" : "" %>' +
      '* Copyright (c) <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>;' +
      ' Licensed <%= _.pluck(pkg.licenses, "type").join(", ") %> */\n',
    conf: {
      name: 'citraining',
      dist: 'src/main/content/jcr_root/media/scripts/citraining/admin',
      src: 'src/main/content/jcr_root/media/scripts/citraining/admin'
    },
    concat: {
      'js': {
        src: [
          
          '<%=conf.dist%>/app.js'		  
        ],
        dest: '<%=conf.src%>/dest/admin.js'
      }
    }
  });

  grunt.registerTask('build', [
    'concat:js'
  ]);
};